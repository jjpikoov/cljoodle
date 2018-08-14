(ns cljoodle.android.components.login-component
  (:require
    [reagent.core :as r :refer [atom]]
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.http.login :as login])
  (:use
    [cljoodle.android.components.common]))

(defn login-component
  []
  (fn []
    (let [login (r/atom "")
          password (r/atom "")
          token (subscribe [:get-token])]
      [view {:style {:flex-direction "column"
                     :margin         40
                     :align-items    "center"}}
       [text {:style {:font-size     50
                      :font-weight   "100"
                      :margin-bottom 20
                      :text-align    "center"}} "Cljoodle"]
       [image {:style  {:width         80
                        :height        80
                        :margin-bottom 30}
               :source logo-img}]
       [text-input {:style        {:width 200}
                    :placeholder  "Login"
                    :onChangeText #(reset! login %)
                    }]
       [text-input {:style        {:width 200}
                    :placeholder  "Password"
                    :onChangeText #(reset! password %)
                    }]
       [touchable-highlight {:style    {:background-color "#999"
                                        :margin-top       30
                                        :padding          10
                                        :border-radius    5}
                             :on-press #(login/set-token-providing-login-password @login @password)}
        [text {:style {:color       "white"
                       :text-align  "center"
                       :font-weight "bold"}} "Login"]]])))
