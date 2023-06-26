package org.cris.preference.exception;

import lombok.Data;
import org.cris.preference.result.ResultCodeEnum;

@Data
public class SsyxException  extends  RuntimeException{
     //异常状态码
    private Integer code;

    public SsyxException(String message,Integer code){
        super(message);
        this.code = code;
    }

    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "PreferenceException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
