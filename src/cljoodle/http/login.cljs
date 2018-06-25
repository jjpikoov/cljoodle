(ns cljoodle.http.login
  (:require [cljoodle.http.moodle.requests :as rq]))

(def login-endpoint "/login/token.php?moodlewsrestformat=json")

(def moodle-login-url
  (str rq/url-and-port login-endpoint))

(defn login-to-moodle
  "Allows to login to moodle providing token"
  []
  (rq/print-post-request-response "91f1cab7104f608a0d06fe4f34cd6e4e" "core_course_get_courses" {}))

(defn get-token-basic-auth
  "Gets token providing login and password"
  [handler login password]
  (rq/do-post-request handler moodle-login-url {:username login
                                                :password password
                                                :service "moodle_mobile_app"}))







