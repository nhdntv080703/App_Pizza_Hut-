package com.example.demo.exception;

//unchecked: extends RuntimeException
//checked: extends Exception
//Ví dụ mình muốn update theo id mà id không có trong database thì mình có thể throw ra 1 exception để bắt, vì nó k tự bắn ra cho mình. Thì dùng RuntimeException
//Còn exception là lỗi ví dụ 10/0 thì không chia được. Khi đó chương trình sẽ lỗi và tự bắn a lỗi. Mình k cần throw
public class FieldRequiredException extends RuntimeException{
    public FieldRequiredException(String errorMessage){
        super(errorMessage);
    }
}
