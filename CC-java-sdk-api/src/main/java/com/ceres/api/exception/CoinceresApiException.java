package com.ceres.api.exception;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class CoinceresApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;

  private CoinceresApiError error;

  public CoinceresApiException(CoinceresApiError error) {
    this.error = error;
  }

  public CoinceresApiException() {
    super();
  }

  public CoinceresApiException(String message) {
    super(message);
  }


  public CoinceresApiException(Throwable cause) {
    super(cause);
  }

  public CoinceresApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public CoinceresApiError getError() {
    return error;
  }

  @Override
  public String getMessage() {
    if (error != null) {
      return error.getMessage();
    }
    return super.getMessage();
  }
}
