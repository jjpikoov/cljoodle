(ns cljoodle.android.components.menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.navigator-component :as nav]
    [cljoodle.http.login :as login]
    ))

(defn menu-component
  []
  [comm/view (nav/title-component "MENU")
   [comm/view {:style {
                       :flex-direction  "column"
                       :flex-wrap       "nowrap"
                       :justify-content "space-between"
                       :margin-top      "5%"
                       :align-items     "stretch"
                       :align-content   "space-between"
                       :height          "90%"
                       }}
    [comm/view {:style {:flex-grow 1}}
     [comm/touchable-highlight {:style    {:background-color "#6e00ce"
                                           :padding          5
                                           :margin           5}
                                :on-press #(prn "Quizes")}
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
                         :font-weight "bold"}} "Logout"]]]])
