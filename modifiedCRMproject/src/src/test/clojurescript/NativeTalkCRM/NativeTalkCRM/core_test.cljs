(ns NativeTalkCRM.NativeTalkCRM.core-test
  (:require [NativeTalkCRM.NativeTalkCRM.core :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest test-reverso (is (= "blah" (c/reverso "halb"))))

