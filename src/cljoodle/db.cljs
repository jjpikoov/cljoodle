(ns cljoodle.db
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::greeting string?)

;; spec of app-db
(spec/def ::app-db
  (spec/keys :req-un [::greeting]))

;; initial state of app-db
(def app-db {:greeting      "Hello Clojure in iOS and Android!"
             :active-view   "login-component"
             :previous-view nil
             :token         ""
             :courses       nil
             :active-course-id nil})
