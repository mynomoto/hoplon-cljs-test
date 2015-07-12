// karma.conf.js
module.exports = function(config) {
  // same as :output-dir
  var root = 'target';

  config.set({
    frameworks: ['cljs-test'],

    files: [
      root + '/out/goog/base.js',
      root + '/out/cljs_deps.js',
      root + '/main.js',// same as :output-to
      {pattern: root + '/out/*.js', included: false},
      {pattern: root + '/out/**/*.js', included: false}
    ],

    browsers: ['PhantomJS'],
    client: {
      // main function
      args: ['hoplon_test.test_runner.run_with_karma']
    },
  });
};
