package com.kfc.exception;

public class UserException extends BusinessException {

    public UserException(int code, String message) {
        super(code, message);
    }

    /**
     * 用户不存在
     */
    public class UserNotFoundException extends UserException {
        public UserNotFoundException() {
            super(1001, "USER_NOT_FOUND");
        }
    }

    /**
     * 用户无权限
     */
    public class UserPermissionDeniedException extends UserException {
        public UserPermissionDeniedException() {
            super(1002, "USER_PERMISSION_DENIED");
        }
    }
}
