package run.mone.ultraman.bo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodjava@qq.com
 * @date 2023/11/6 10:03
 */
@Data
@Builder
public class AthenaClassInfo {

    private String name;

    private String classCode;

    private String md5;

    private List<String> annoList;

    private List<String> publicMethodList;

    @Builder.Default
    private List<String> interfaceList = new ArrayList<>();

}
