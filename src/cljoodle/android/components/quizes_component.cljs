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

(defn _convert-course-to-menu-component-format
  [course]
  {:title    (:shortname course)
   :on-press #(prn (:shortname course))})


(defn quizes-component
  []
  (let
    [courses (subscribe [:get-courses])]
    [comm/view (nav/navigator-component "Quizes")
     [comm/view {:style {:flex-direction  "column"
                         :flex-wrap       "nowrap"
                         :justify-content "space-between"
                         :margin-top      "5%"
                         :align-items     "stretch"
                         :align-content   "space-between"
                         :height          "90%"
                         }}
      [comm/text {:style {:font-size     20
                          :font-weight   "100"
                          :margin-bottom 20
                          :text-align    "center"}} "Choose course"]

      (into [comm/scroll-view]
            (menu/menu-list-component (map _convert-course-to-menu-component-format @courses)))]])
  )
