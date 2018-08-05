(ns cljoodle.android.core
  (:require
    [reagent.core :as r :refer [atom]]
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.events]
    [cljoodle.subs]
    [cljoodle.http.login]))


(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

(def logo-img (js/require "./images/cljs.png"))

(defn alert [title]
  (.alert (.-Alert ReactNative) title))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [text {:style {:font-size     50
                      :font-weight   "100"
                      :margin-bottom 20
                      :text-align    "center"}} "Cljoodle"]
       [image {:source logo-img
               :style  {:width         80
                        :height        80
                        :margin-bottom 30}}]
       [text-input {:placeholder  "Login"
                    :style        {:width 200}
                    :onEndEditing #(dispatch [:set-login (-> % .-value)])}]
       [text-input {:placeholder  "Password" :style {:width 200}
                    :onEndEditing #(dispatch [:set-password (-> % .-value)])}]
       [touchable-highlight {:style    {:background-color "#999"
                                        :margin-top       30
                                        :padding          10
                                        :border-radius    5}
                             :on-press #(alert "HELLO!")}
        [text {:style {:color       "white"
                       :text-align  "center"
                       :font-weight "bold"}} "Login"]]])))
[
 (defn init []
   (dispatch-sync [:initialize-db])
   (.registerComponent app-registry "cljoodle" #(r/reactify-component app-root)))]
