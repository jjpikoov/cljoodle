(ns cljoodle.android.components.quizes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.navigator-component :as nav]
    [cljoodle.android.components.menu-component :as menu]
    ))


(defn quizes-component
  []
  [comm/view
   [comm/view (menu/menu-component "Quizes" menu/items)]]
  )
