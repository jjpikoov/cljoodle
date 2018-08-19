(ns cljoodle.android.components.common
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def button-with-styles (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

(def logo-img (js/require "./images/cljoodle.png"))
