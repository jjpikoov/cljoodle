(ns cljoodle.android.components.menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.navigator-component :as nav]
    [cljoodle.http.login :as login]
    [cljoodle.http.courses :as courses]

    ))

(defn main-menu-component
  []
  (let
    [token (subscribe [:get-token])]
    (courses/get-courses (fn [crs] (dispatch [:set-courses crs]))
                         @token)
    [comm/view (nav/title-component "MENU")
     [comm/view {:style {
                         :flex-direction  "column"
                         :flex-wrap       "nowrap"
                         :justify-content "space-between"
                         :margin-top      "5%"
                         :align-items     "stretch"
                         :align-content   "space-between"
                         :height          "85%"
                         }}
      [comm/scroll-view {:style {:flex-grow 1}}
       [comm/touchable-highlight {:style    {:background-color "#6e00ce"
                                             :padding          5
                                             :margin           5}
                                  :on-press #(dispatch [:set-active-view "quizes-component"])}
        [comm/text {:style {:color       "white"
                            :text-align  "center"
                            :font-weight "bold"}} "Quizes"]]

       [comm/touchable-highlight {:style    {:background-color "#6e00ce"
                                             :padding          5
                                             :margin           5}
                                  :on-press #(prn "FOOO")}
        [comm/text {:style {:color       "white"
                            :text-align  "center"
                            :font-weight "bold"}} "FOOO"]]]
      [comm/touchable-highlight {:style    {:background-color "#c242f4"
                                            :padding          10
                                            :margin           5}
                                 :on-press #(login/logout)}
       [comm/text {:style {:color       "white"
                           :text-align  "center"
                           :font-weight "bold"}} "Logout"]]]]))


(defn menu-list-component
  [data]
  (loop [remaining-data data
         converted-data []]
    (if (empty? remaining-data)
      converted-data
      (let [[head & rest] remaining-data]
        (recur rest
               (into converted-data
                     [[comm/touchable-highlight {:style    {:background-color "#6e00ce"
                                                            :padding          5
                                                            :margin           5}
                                                 :on-press (:on-press head)}
                       [comm/text {:style {:color       "white"
                                           :text-align  "center"
                                           :font-weight "bold"}} (:title head)]]]
                     ))))))
