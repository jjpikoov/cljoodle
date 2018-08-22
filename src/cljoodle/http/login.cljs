(ns cljoodle.http.login
  (:require [cljoodle.http.util.requests :as requests]
            [env.moodle.config :as moodle-config]))

(def _login-endpoint (str "/login/token.php"
                          "?" moodle-config/moodle-json-wrap-response-url-param))

(def _moodle-login-url
  (str moodle-config/url-and-port
       _login-endpoint))

(defn get-token-providing-login-password
  "Tries to obtain token for given credentials, if succeeds dispatches token change action"
  [handler login password]
  (let [retrieve-token-from-response-func #(:token (:body %))]
    (requests/do-post-request #(-> %
                                   retrieve-token-from-response-func
                                   handler)
                              _moodle-login-url
                              {:username login
                               :password password
                               :service  "moodle_mobile_app"})))
