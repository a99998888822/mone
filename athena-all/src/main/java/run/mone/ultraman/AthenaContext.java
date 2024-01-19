package run.mone.ultraman;

import com.intellij.openapi.project.Project;
import run.mone.m78.ip.bo.ModelRes;
import run.mone.m78.ip.listener.UltrmanTreeKeyAdapter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.mone.ultraman.bo.ClientData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author goodjava@qq.com
 * @date 2023/6/28 08:03
 */
@Data
public class AthenaContext {

    private ConcurrentHashMap<String, Project> projectMap = new ConcurrentHashMap<>();

    private UltrmanTreeKeyAdapter athenaTreeKeyAdapter;

    private int jdkVersion;

    private String zAddr;

    private List<ModelRes> models;

    private Map<String, ModelRes> modelMap;


    //models 直接 转换为 modelMap,key是 ModelRes中的 value(class)
    public void convertModelsToModelMap() {
        if (models != null) {
            modelMap = models.stream().collect(Collectors.toMap(ModelRes::getValue, Function.identity(), (existing, replacement) -> existing));
        }
    }


    public ModelRes getModel(String name) {
        return modelMap.get(name);
    }

    //客户端的数据
    private ConcurrentHashMap<String, ClientData> clientDataMap = new ConcurrentHashMap<>();


    //获取范围
    public ClientData getClientData(String projectName) {
        ClientData data = clientDataMap.get(projectName);
        if (null == data) {
            return ClientData.builder().scope("method").build();
        }
        return data;
    }

    /**
     * chatgpt的模型
     */
    private String gptModel = "";

    public int getMaxTokenNum() {
        return getModel(gptModel).getMaxToken();
    }

    public ModelRes gptModel() {
        if (StringUtils.isEmpty(this.gptModel)) {
            return null;
        }
        return getModel(this.gptModel);
    }

    private List<String> modelList = new ArrayList<>();

    private String token;




    private boolean debugAiProxy;

    private static final class LazyHolder {
        private static final AthenaContext ins = new AthenaContext();
    }

    public static AthenaContext ins() {
        return LazyHolder.ins;
    }

}
