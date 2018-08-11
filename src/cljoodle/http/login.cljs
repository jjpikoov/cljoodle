(ns cljoodle.http.login
  (:require [cljoodle.http.moodle.requests :as rq]
            [env.moodle.config :as moodle-config])
  (:use [re-frame.core :only [dispatch]]))

(def _moodle-login-url
  (str moodle-config/url-and-port moodle-config/login-endpoint))

(defn login-to-moodle                                       ;; TODO is this necessary
  "Allows to login to moodle providing token"
  []
  (rq/print-post-request-response moodle-config/wstoken "core_course_get_courses" {}))

(defn set-token-providing-login-password
  "Tries to obtain token for given credentials, if succeeds dispatches token change action"
  [login password]
  (let [retrieve-token-from-response-func #(:token (:body %))
        dispatch-token-change (fn [token] (dispatch [:set-token token]))]
    (rq/do-post-request #(-> %
                             retrieve-token-from-response-func
                             dispatch-token-change)
                        _moodle-login-url
                        {:username login
                         :password password
                         :service  "moodle_mobile_app"})))

