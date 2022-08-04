var exec = require('cordova/exec');

exports.requestReview = function (success, error) {
    exec(success, error, 'InAppReview', 'requestReview');
};
