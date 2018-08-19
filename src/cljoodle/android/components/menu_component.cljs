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
   [comm/view {:style {:flex-direction "column"
                       :margin-top     20
                       :margin         5
                       :align-items    "center"}}
    [comm/touchable-highlight {:style    {:background-color "#6e00ce"
                                          :padding          10
                                          :margin           10
                                          :width            "100%"}
                               :on-press #(login/logout)}
     [comm/text {:style {:color       "white"
                         :text-align  "center"
                         :font-weight "bold"}} "Logout"]]
    ]])
