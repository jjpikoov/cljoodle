(ns cljoodle.android.components.quizes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.navigator-component :as nav]
    ))


(defn quizes-component
  []
  [comm/view (nav/navigator-component "Quizes")])
