(ns cljoodle.android.components.quizes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.navigator-component :as nav]
    [cljoodle.android.components.menu-component :as menu]
    ))

(defn _generate-test-data
  [items-count]
  (loop [i 0
         acc []]
    (if (= i items-count)
      acc
      (recur (inc i)
             (into acc [{:title    i
                         :on-press #(prn i)}]))))
  )

(defn quizes-component
  []
  [comm/view
   [comm/view (menu/menu-component "Quizes" (_generate-test-data 100))]]
  )
