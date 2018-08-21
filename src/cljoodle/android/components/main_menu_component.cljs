(ns cljoodle.android.components.main-menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.title-component :as title]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.http.courses :as courses]

    ))

(defn _generate-menu-item
  [title on-press-func]
  [rw/touchable-highlight {:style    {:background-color "#6e00ce"
                                      :padding          5
                                      :margin           5}
                           :on-press on-press-func}
   [rw/text {:style {:color       "white"
                     :text-align  "center"
                     :font-weight "bold"}} title]])

(defn main-menu-component
  []
  (let
    [token (subscribe [:get-token])]
    (courses/get-courses (fn [crs] (dispatch [:set-courses crs]))
                         @token)
    (dispatch [:set-active-course-id nil])
    [rw/view (title/title-component "MENU")
     [rw/view (assoc styles/items-list-container-style :height "85%")
      [rw/scroll-view {:style {:flex-grow 1}}
       (_generate-menu-item "Quizzes" #(dispatch [:set-active-view "quizzes-component"]))
       (_generate-menu-item "FOO" #(prn "FOOO"))]
      [rw/touchable-highlight {:style    {:background-color "#c242f4"
                                          :padding          10
                                          :margin           5}
                               :on-press #(dispatch [:set-token ""])}
       [rw/text {:style {:color       "white"
                         :text-align  "center"
                         :font-weight "bold"}} "Logout"]]]]))
