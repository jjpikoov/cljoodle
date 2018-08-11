(ns cljoodle.http.login
  (:require [cljoodle.http.moodle.requests :as requests]
            [env.moodle.config :as moodle-config])
  (:use [re-frame.core :only [dispatch]]))

(def _moodle-login-url
  (str moodle-config/url-and-port
       moodle-config/login-endpoint))

(defn set-token-providing-login-password
  "Tries to obtain token for given credentials, if succeeds dispatches token change action"
  [login password]
  (let [retrieve-token-from-response-func #(:token (:body %))
        dispatch-token-change (fn [token] (dispatch [:set-token token]))]
    (requests/do-post-request #(-> %
                                   retrieve-token-from-response-func
                                   dispatch-token-change)
                              _moodle-login-url
                              {:username login
                               :password password
                               :service  "moodle_mobile_app"})))
