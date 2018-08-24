(ns cljoodle.android.components.login-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.http.login :as login]))

(defn wrong-login-password-component
  [token]
  (if (nil? token)
    [rw/text {:style {:font-size  15
                      :margin-top 15
                      :color      "red"
                      :text-align "center"}} "Wrong login or/and password!"]))

(defn login-component
  []
  (let [login (r/atom "")
        password (r/atom "")
        token (subscribe [:get-token])]
    [rw/view {:style {:flex-direction "column"
                      :margin         40
                      :align-items    "center"}}
     [rw/text {:style {:font-size     40
                       :font-weight   "100"
                       :margin-bottom 20
                       :text-align    "center"}} "Cljoodle!"]
     [rw/image {:style  {:width      300
                         :height     100
                         :resizeMode "contain"
                         :margin     10}
                :source rw/logo-img}]
     [rw/view (wrong-login-password-component @token)]
     [rw/text-input {:style        {:width 200}
                     :placeholder  "Login"
                     :onChangeText #(reset! login %)
                     }]
     [rw/text-input {:style           {:width 200}
                     :placeholder     "Password"
                     :secureTextEntry true
                     :onChangeText    #(reset! password %)
                     }]
     [rw/touchable-highlight {:style    {:background-color "#999"
                                         :margin-top       30
                                         :padding          10
                                         :border-radius    5}
                              :on-press #(login/get-token-providing-login-password
                                           (fn [token] (dispatch [:set-token token]))
                                           @login @password)}
      [rw/text {:style {:color       "white"
                        :text-align  "center"
                        :font-weight "bold"}} "Login"]]]))
