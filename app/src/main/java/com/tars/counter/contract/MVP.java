package com.tars.counter.contract;

import android.view.View;

import com.tars.counter.model.Counter;

/**
 * MVP interface.
 * MVP view is used for presenting information to the user.
 * The Activities are used as Presenters and has the business logic
 */
public interface MVP {

        /**
         * Get the root Android View which is used internally by this MVP View for presenting data
         * to the user
         * @return root Android View of this MVP View
         */
        View getRootView();

    interface PresenterMain {

    }

    interface PresenterNewCount {
        void saveACounter(Counter newCounter);
    }

    interface NewCountView extends MVP {
        void saveACounter(boolean saved);
    }

    interface MainView extends MVP {

    }
}