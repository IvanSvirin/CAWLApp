package com.cawlapp.cawlapp.presentation.view.fragment;

import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.cawlapp.cawlapp.presentation.model.StoreModel;

public class AllStoresFragment extends BaseFragment{
    /**
     * Interface for listening deal list events.
     */
    public interface StoresListListener {
        void onStoreClicked(final StoreModel storeModel);
    }

}
