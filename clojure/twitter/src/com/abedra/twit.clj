(ns com.abedra.twit
  (:use clj-android)
  (:require com.abedra.twitter)
  (:import (com.abedra.twit R$layout R$id)
           (android.view Window)
           (android.widget ArrayAdapter)))

(defactivity PublicTimeline (:extends android.app.ListActivity)
  (:create (.requestWindowFeature context Window/FEATURE_INDETERMINATE_PROGRESS)
           (.setContentView context R$layout/public_timeline))
  (:resume (.start (new Thread (fn []
                                 (ui (.setProgressBarIndeterminateVisibility context true))
                                 (let [statuses (into-array (map #(str "@" ((% :user) :screen_name) ": " (% :text)) (com.abedra.twitter/public-timeline)))]
                                   (ui (.setListAdapter context (new ArrayAdapter context android.R$layout/simple_list_item_1 statuses))))
                                 (ui (.setProgressBarIndeterminateVisibility context false)))))))

(defactivity Main
  (:create (.setContentView context R$layout/main)
           (start-activity PublicTimeline)))
