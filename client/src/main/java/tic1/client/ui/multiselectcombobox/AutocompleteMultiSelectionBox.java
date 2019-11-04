package tic1.client.ui.multiselectcombobox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AutocompleteMultiSelectionBox extends HBox {

    private final ObservableList<String> tags;
    private final ObservableSet<String> suggestions;
    private ContextMenu entriesPopup;
    private static final int MAX_ENTRIES = 10;

    private final TextField inputTextField;

    public AutocompleteMultiSelectionBox() {
        getStyleClass().setAll("tag-bar");
        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        tags = FXCollections.observableArrayList();
        suggestions = FXCollections.observableSet();
        inputTextField = new TextField();
        this.entriesPopup = new ContextMenu();
        setListner();
        inputTextField.setOnKeyPressed(event -> {
            // Remove last element with backspace
            if (event.getCode().equals(KeyCode.BACK_SPACE) && !tags.isEmpty() && inputTextField.getText().isEmpty()) {
                String last = tags.get(tags.size() - 1);
                suggestions.add(last);
                tags.remove(last);
            }
        });

        inputTextField.prefHeightProperty().bind(this.heightProperty());
        HBox.setHgrow(inputTextField, Priority.ALWAYS);
        inputTextField.setBackground(null);

        tags.addListener((ListChangeListener.Change<? extends String> change) -> {
            while (change.next()) {
                if (change.wasPermutated()) {
                    ArrayList<Node> newSublist = new ArrayList<>(change.getTo() - change.getFrom());
                    for (int i = change.getFrom(), end = change.getTo(); i < end; i++) {
                        newSublist.add(null);
                    }
                    for (int i = change.getFrom(), end = change.getTo(); i < end; i++) {
                        newSublist.set(change.getPermutation(i), getChildren().get(i));
                    }
                    getChildren().subList(change.getFrom(), change.getTo()).clear();
                    getChildren().addAll(change.getFrom(), newSublist);
                } else {
                    if (change.wasRemoved()) {
                        getChildren().subList(change.getFrom(), change.getFrom() + change.getRemovedSize()).clear();
                    }
                    if (change.wasAdded()) {
                        getChildren().addAll(change.getFrom(), change.getAddedSubList().stream().map(Tag::new).collect(
                                Collectors.toList()));
                    }
                }
            }
        });
        getChildren().add(inputTextField);
    }

    /**
     * Build TextFlow with selected text. Return "case" dependent.
     *
     * @param text   - string with text
     * @param filter - string to select in text
     * @return - TextFlow
     */
    private static TextFlow buildTextFlow(String text, String filter) {
        int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + filter.length()));
        Text textFilter = new Text(text.substring(filterIndex,
                filterIndex + filter.length())); //instead of "filter" to keep all "case sensitive"
        textFilter.setFill(Color.ORANGE);
        textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        return new TextFlow(textBefore, textFilter, textAfter);
    }

    /**
     * "Suggestion" specific listners
     */
    private void setListner() {
        //Add "suggestions" by changing text
        inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            //always hide suggestion if nothing has been entered (only "spacebars" are dissalowed in TextFieldWithLengthLimit)
            if (newValue.isEmpty()) {
                entriesPopup.hide();
            } else {
                //filter all possible suggestions depends on "Text", case insensitive
                List<String> filteredEntries = suggestions.stream()
                        .filter(e -> e.toLowerCase().contains(newValue.toLowerCase()))
                        .collect(Collectors.toList());
                //some suggestions are found
                if (!filteredEntries.isEmpty()) {
                    //build popup - list of "CustomMenuItem"
                    populatePopup(filteredEntries, newValue);
                    if (!entriesPopup.isShowing()) { //optional
                        entriesPopup.show(this, Side.BOTTOM, 0, 0); //position of popup
                    }
                    //no suggestions -> hide
                } else {
                    entriesPopup.hide();
                }
            }
        });

        //Hide always by focus-in (optional) and out
        focusedProperty().addListener((observableValue, oldValue, newValue) -> entriesPopup.hide());
    }

    /**
     * Populate the entry set with the given search results. Display is limited to 10 entries, for performance.
     *
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult, String searchRequest) {
        //List of "suggestions"
        List<CustomMenuItem> menuItems = new LinkedList<>();
        //Build list as set of labels
        searchResult.stream()
                .limit(MAX_ENTRIES) // Limit to MAX_ENTRIES in the suggestions
                .forEach(result -> {
                    //label with graphic (text flow) to highlight founded subtext in suggestions
                    TextFlow textFlow = buildTextFlow(result, searchRequest);
                    textFlow.prefWidthProperty().bind(AutocompleteMultiSelectionBox.this.widthProperty());
                    CustomMenuItem item = new CustomMenuItem(textFlow, true);
                    menuItems.add(item);

                    //if any suggestion is select set it into text and close popup
                    item.setOnAction(actionEvent -> {
                        tags.add(result);
                        suggestions.remove(result);
                        inputTextField.clear();
                        entriesPopup.hide();
                    });
                });

        //"Refresh" context menu
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }

    public final ObservableList<String> getTags() {
        return tags;
    }

    public final ObservableSet<String> getSuggestions() {
        return suggestions;
    }

    /**
     * Clears then repopulates the entries with the new set of data.
     *
     * @param suggestions set of items.
     */

    public final void setSuggestions(ObservableSet<String> suggestions) {
        this.suggestions.clear();
        this.suggestions.addAll(suggestions);
    }

    private class Tag extends HBox {
        Tag(String tag) {
            // Style
            getStyleClass().add("tag");

            // Remove item button
            Button removeButton = new Button("x");
            removeButton.setBackground(null);
            removeButton.setOnAction(event -> {
                tags.remove(tag);
                suggestions.add(tag);
                inputTextField.requestFocus();
            });

            // Displayed text
            Text text = new Text(tag);
            text.setFill(Color.WHITE);
            text.setFont(Font.font(text.getFont().getFamily(), FontWeight.BOLD, text.getFont().getSize()));

            // Children position
            setAlignment(Pos.CENTER);
            setSpacing(5);
            setPadding(new Insets(0, 0, 0, 5));

            getChildren().addAll(text, removeButton);
        }
    }

}
