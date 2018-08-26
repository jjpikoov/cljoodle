(ns cljoodle.http.login
  (:require [cljoodle.http.util.requests :as requests]
            [env.moodle.config :as moodle-config]))

(def login-endpoint (str "/login/token.php"
                         "?" moodle-config/moodle-json-wrap-response-url-param))

(def moodle-login-url
  (str moodle-config/url-and-port
       login-endpoint))

(defn get-token-providing-login-password
  "Tries to obtain token for given credentials, if succeeds dispatches token change action"
  [handler login password]
  (let [retrieve-token-from-response-func #(:token (:body %))]
    (requests/do-post-request #(-> %
                                   retrieve-token-from-response-func
                                   handler)
                              moodle-login-url
                              {:username login
                               :password password
                               :service  "moodle_mobile_app"})))
