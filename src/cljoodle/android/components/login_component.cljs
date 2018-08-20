(ns cljoodle.android.components.login-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.android.components.common :as comm]
    [cljoodle.http.login :as login]))

(defn wrong-login-password-component
  [token]
  (if (nil? token)
    [comm/text {:style {:font-size  15
                        :margin-top 15
                        :color      "red"
                        :text-align "center"}} "Wrong login or/and password!"]))

(defn login-component
  []
  (let [login (r/atom "")
        password (r/atom "")
        token (subscribe [:get-token])]
    [comm/view {:style {:flex-direction "column"
                        :margin         40
                        :align-items    "center"}}
     [comm/text {:style {:font-size     40
                         :font-weight   "100"
                         :margin-bottom 20
                         :text-align    "center"}} "Cljoodle!"]
     [comm/image {:style  {:width      300
                           :height     100
                           :resizeMode "contain"
                           :margin     10}
                  :source comm/logo-img}]
     [comm/view (wrong-login-password-component @token)]
     [comm/text-input {:style        {:width 200}
                       :placeholder  "Login"
                       :onChangeText #(reset! login %)
                       }]
     [comm/text-input {:style           {:width 200}
                       :placeholder     "Password"
                       :secureTextEntry true
                       :onChangeText    #(reset! password %)
                       }]
     [comm/touchable-highlight {:style    {:background-color "#999"
                                           :margin-top       30
                                           :padding          10
                                           :border-radius    5}
                                :on-press #(login/set-token-providing-login-password @login @password)}
      [comm/text {:style {:color       "white"
                          :text-align  "center"
                          :font-weight "bold"}} "Login"]]]))
