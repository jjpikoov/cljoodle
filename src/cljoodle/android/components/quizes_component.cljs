(ns cljoodle.android.components.quizes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.converter.courses-converter :as cc]
    ))

(defn quizes-component
  []
  (let
    [courses (subscribe [:get-courses])]
    [rw/view (nav/navigator-component "Quizes")
     [rw/view {:style {:flex-direction  "column"
                       :flex-wrap       "nowrap"
                       :justify-content "space-between"
                       :margin-top      "5%"
                       :align-items     "stretch"
                       :align-content   "space-between"
                       :height          "90%"}}
      [rw/text {:style {:font-size     20
                        :font-weight   "100"
                        :margin-bottom 20
                        :text-align    "center"}} "Choose course"]

      (into [rw/scroll-view]
            (menu-list/menu-list-component
              (map cc/convert-course-to-menu-component-format @courses)))]])
  )
