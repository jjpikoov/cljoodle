;;; File declares initial state and its structure
(ns cljoodle.db
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::active-view string?)

;; spec of app-db
(spec/def ::app-db
  (spec/keys :req-un [::active-view]))

;; initial state of app-db
(def app-db {:active-view      "login-component"
             :previous-view    nil
             :views-history    (list)
             :token            ""
             :courses          nil
             :active-course-id nil
             :quizzes          nil
             :events           nil
             ; new event
             :event-new-name   nil
             :event-new-day    1
             :event-new-month  1
             :event-new-year   2018
             :event-new-desc   nil})
