;;; File declares component for showing main menu
(ns cljoodle.android.components.main-menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.title-component :as title]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.http.courses :as courses]))

(defn- generate-menu-item
  "Generates main menu Hiccup item with given title and function to trigger when item is tapped"
  [title on-press-func]
  [rw/touchable-highlight {:style    {:background-color "#6e00ce"
                                      :padding          5
                                      :margin           5}
                           :on-press on-press-func}
   [rw/text {:style {:color       "white"
                     :text-align  "center"
                     :font-weight "bold"}} title]])

(defn main-menu-component
  "Function returns Hiccup data for showing main menu.

  It fetches all available courses at this moment to speed up further actions.
  It also dispatches action of clearing active course id.
  If user taps logout button it clears token."
  []
  (let
    [token (subscribe [:get-token])]
    (courses/get-courses (fn [crs] (dispatch [:set-courses crs]))
                         @token)
    (dispatch [:set-active-course-id nil])
    [rw/view (title/title-component "MENU")
     [rw/view (assoc styles/item-list-container-style :height "85%")
      [rw/scroll-view {:style {:flex-grow 1}}
       (generate-menu-item "Quizzes" #(dispatch [:set-active-view "quizzes-component"]))
       (generate-menu-item "Events" #(dispatch [:set-active-view "events-component"]))]
      [rw/touchable-highlight {:style    {:background-color "#c242f4"
                                          :padding          10
                                          :margin           5}
                               :on-press #(dispatch [:set-token ""])}
       [rw/text {:style {:color       "white"
                         :text-align  "center"
                         :font-weight "bold"}} "Logout"]]]]))
