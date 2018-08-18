(ns cljoodle.android.components.menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.http.login :as login]))

(defn menu-component
  []
  (fn []
    [comm/view {:style {:flex-direction "column"
                        :margin         40
                        :align-items    "center"}}
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     ]))
