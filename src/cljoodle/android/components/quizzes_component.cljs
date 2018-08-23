(ns cljoodle.android.components.quizzes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.converter.quizzes-converter :as qc]
    [cljoodle.http.quizzes :as quiz]
    [reagent.core :as r]
    ))

(defn set-extra-info-for-quizzes
  [token quizzes]
  (let [__can-attempt (atom nil)
        __questions-types (atom nil)]
    (loop
      [retrieved-quizzes quizzes
       quizzes-with-info []]
      (if (empty? retrieved-quizzes)
        (do
          (dispatch [:set-quizzes quizzes-with-info]))
        (let [[head & rest] retrieved-quizzes
              quiz-id (:id head)]
          (do
            (quiz/get-quiz-eligibility #(reset! __can-attempt %) token quiz-id)
            (quiz/get-quiz-type #(reset! __questions-types %) token quiz-id)
            (recur rest
                   (conj quizzes-with-info
                         (assoc head :can-attempt @__can-attempt
                                     :question-types @__questions-types)))))))))

(defn quizzes-component
  []
  (let
    ; subscribe
    [course-id (subscribe [:get-active-course-id])
     quizzes-final (subscribe [:get-quizzes])
     token (subscribe [:get-token])
     ; util functions
     converting-function (partial qc/convert-quiz-to-menu-item-format
                                  (fn [id] (fn [] (prn id))))
     converted-quizes (map converting-function @quizzes-final)
     ]
    ; fetch data
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"])
      (do
        (if (nil? @quizzes-final)
          (quiz/get-quizzes #(set-extra-info-for-quizzes @token %)
                            @token
                            @course-id))))
    ; render
    [rw/view (nav/navigator-component "Quizzes")
     [rw/view styles/items-list-container-style
      [rw/text {:style {:font-size     20
                        :font-weight   "100"
                        :margin-bottom 20
                        :text-align    "center"}} "Choose quiz"]
      (into [rw/scroll-view]
            (menu-list/menu-list-component converted-quizes))
      ]]))


