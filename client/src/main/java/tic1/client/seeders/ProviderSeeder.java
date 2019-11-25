package tic1.client.seeders;

import tic1.client.models.Provider;
import tic1.client.services.ProviderRestTemplate;
import tic1.commons.transfers.ProviderDTO;

public class ProviderSeeder {
    public static void main(String[] args) {
        ProviderDTO providerDTO = new ProviderDTO();
        ProviderRestTemplate providerRestTemplate = new ProviderRestTemplate();
        Provider provider = new Provider(providerDTO);
       for(int i =0;i<10;i++) {
           provider.setName("juanitos"+i);
           providerRestTemplate.createProvider(provider);
       }
    }
}
