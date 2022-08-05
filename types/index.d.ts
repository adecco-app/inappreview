interface InAppReview {
  /**
   * Launches in-app review dialog.
   *
   * @returns {Promise<void>} Callback when operation is completed
   *
   * @example
   * cordova.plugins.AppReview.requestReview();
   */
   requestReview(): Promise<void>;
}

interface Cordova {
    InAppReview: InAppReview;
}
