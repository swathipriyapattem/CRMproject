(ns NativeTalkCRM.NativeTalkCRM.test-suite
  (:require [NativeTalkCRM.NativeTalkCRM.core-test :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(defn -main [args] (run-tests 'NativeTalkCRM.NativeTalkCRM.core-test))

;; (run-tests 'com.example.foo.core-test)
