(ns hoplon-test.core-test
  (:require-macros
    [tailrecursion.javelin :refer [defc]])
  (:require
    [cljs.test :refer-macros [deftest is testing run-tests async]]
    [dommy.core :as dommy :refer-macros [sel sel1]]
    [dommy.utils :as utils]
    [hoplon-test.core :as c]
    [tailrecursion.javelin :refer [cell]]))

(defc c (cell nil))
(def word-item (c/word-item c))

(deftest word-item-element
  (async done
    (reset! c "a-word")
    (is (= (dommy/text word-item) "a-word"))
    (reset! c "another-word")
    (is (= (dommy/text word-item) "another-word"))
    (done)))

(defc state ["abcdef"])
(def tops-component (c/tops-component state))

(deftest tops-element
  (async done
    (dommy/append! js/document.body tops-component)
    (let [elc (.-firstChild tops-component)
          h (aget (.-childNodes elc) 0)
          i (js/jQuery (sel1 :input))
          wl (sel1 :ul)]
      (is (= (.-childElementCount tops-component) 1))
      (is (= (.-childElementCount wl) 1))
      (is (= (dommy/text (.-firstChild wl)) "abcdef"))
      (is (= (.val i) ""))
      (.focus i)
      (.val i "blabla")
      (is (= (.val i) "blabla"))
      (.change i)
      (is (= (dommy/text (sel1 :button)) "Submit"))
      (.click (aget (js/jQuery "button") 0))
      (is (= (.-childElementCount wl) 2))
      (is (= (dommy/text (.-firstChild wl)) "blabla")))
    (done)))
