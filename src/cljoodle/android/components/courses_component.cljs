;;; File declares component for choosing course
(ns cljoodle.android.components.courses-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.converter.courses-converter :as cc]
    [cljoodle.android.components.common.styles :as styles]))


(defn courses-component
  "Function returns Hiccup data for course selection view

  It clears out all db quizzes and events"
  []
  (dispatch [:set-quizzes nil])
  (dispatch [:set-events nil])
  (let
    [courses (subscribe [:get-courses])]
    [rw/view (nav/navigator-component "Courses")
     [rw/view styles/item-list-container-style
      [rw/text {:style {:font-size     20
                        :font-weight   "100"
                        :margin-bottom 20
                        :text-align    "center"}} "Choose course"]
      (into [rw/scroll-view]
            (menu-list/menu-list-component
              (let
                [converting-function (partial
                                       cc/convert-course-to-menu-item-format
                                       (fn [id]
                                         (fn [] (dispatch [:set-active-course-id id]))))]
                (map converting-function @courses))))]]))
