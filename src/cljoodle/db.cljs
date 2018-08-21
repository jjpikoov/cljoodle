(ns cljoodle.db
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::active-view string?)

;; spec of app-db
(spec/def ::app-db
  (spec/keys :req-un [::active-view]))

;; initial state of app-db
(def app-db {:active-view      "login-component"
             :previous-view    nil
             :token            ""
             :courses          nil
             :active-course-id nil})
