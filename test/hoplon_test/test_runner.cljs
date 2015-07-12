(ns hoplon-test.test-runner
  (:require [cljs.test :refer-macros [run-tests]]
            [jx.reporter.karma :refer-macros [tests-count]]
            [hoplon-test.core-test]))

(set-print-fn! #(.log js/console %))

(defn run [env]
  (run-tests env
             'hoplon-test.core-test))

(defn ^:export run-with-karma [tc]
  (do (jx.reporter.karma/start tc (tests-count foo.bar))
      (run (cljs.test/empty-env :jx.reporter.karma/karma))))
