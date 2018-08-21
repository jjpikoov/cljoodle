(ns cljoodle.android.components.quizzes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.converter.courses-converter :as cc]
    ))

(defn quizzes-component
  []
  (let
    [course-id (subscribe [:get-active-course-id])]
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"]))
    [rw/view (nav/navigator-component "Quizzes")
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
                        :text-align    "center"}} "Choose quiz"]
      ;(into [rw/scroll-view]
      ;      (menu-list/menu-list-component
      ;        (let
      ;          [quizzes ]
      ;          [converting-function (partial cc/convert-course-to-menu-component-format
      ;                                        (fn [id]
      ;                                          (fn [] (dispatch [:set-active-course-id id]))))]
      ;          (map converting-function @courses))))
      ]
     ]))
