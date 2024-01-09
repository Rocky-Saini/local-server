package com.digital.signage.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WNSConfig {
    private static final Map<String, Map<String, String>> wnsMap;

    private WNSConfig() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    static {
        // wnsMap is a map of "activeProfile" and a singleton map of "clientSecret" to "clientId"

        wnsMap = new HashMap<>();
        wnsMap.put("dev", Collections.singletonMap("+0ZEEEX71FAngfna5H+uyIlL328Vr+lo",
                "ms-app://s-1-15-2-3249291079-2626716603-94697252-1629332473-301689108-471940643-574348954"));
//        wnsMap.put("dev", Collections.singletonMap("qt0CzPB5ZqW0r1a9pI6IIILfsgTQ3eYJ",
//                "ms-app://S-1-15-2-1062818233-1226576475-3852324549-4154102946-2869573742-962605169-2808435907"));
        wnsMap.put("qa", Collections.singletonMap("g2II/N30Cmb2CzOVTbbt3MJiPGkZMABR",
                "ms-app://S-1-15-2-1938916146-2876844224-4062732424-975616632-4253157035-972703281-1892244851"));
        wnsMap.put("angular-dev", Collections.singletonMap("9qfyvhOG9V2aZU7esVrU2eS8QI1HzBds",
                "ms-app://s-1-15-2-217560044-1741238346-3802603896-893081956-873127978-3075029619-336257499"));

        //pan enviornment
        wnsMap.put("pan-dev", Collections.singletonMap("O2D9qbWT8w12dYGdbDk3Q1K5Rreqs8Z5",
                "ms-app://S-1-15-2-2802814870-2836797854-2017015201-4211103129-2105811041-1354947854-4177436791"));
        wnsMap.put("pan-qa", Collections.singletonMap("+2vxi/94E7EOolX1eIXAC4uvr58bVVz3",
                "ms-app://S-1-15-2-3308632172-3720604857-1178844045-2792233127-3539258387-4001140532-2497743436"));

        //pi enviorment
        wnsMap.put("pidev", Collections.singletonMap("XYB5vgs1XUzpSxQw3SPLvAipzinAUfTT",
                "ms-app://S-1-15-2-1865727564-3948381315-1451228628-1043694189-4076149422-1699513699-2619532521"));
        wnsMap.put("piqa1", Collections.singletonMap("EiOUG8AWEBmcIalG9GXkj1BBVLuljuuc",
                "ms-app://S-1-15-2-2191034534-941247079-4077164290-873390294-2362530921-344122125-3384824491"));
        wnsMap.put("piqa2", Collections.singletonMap("fZ5HILVxj5LozZgHwsbAswL6+r6L6vbL",
                "ms-app://S-1-15-2-2738843327-3730069808-1103787721-2975115554-2652198975-3439429592-3453462824"));

        // pionpremises and pionpremises2
        wnsMap.put("pionpremises", Collections.singletonMap("CP/+pupZaM3Y78LFcpSTftay3mBZiEi+",
                "ms-app://S-1-15-2-572841610-966501214-1892796787-3956688266-3779525307-1193746774-3746984934"));
        wnsMap.put("pionpremises2", Collections.singletonMap("CP/+pupZaM3Y78LFcpSTftay3mBZiEi+",
                "ms-app://S-1-15-2-572841610-966501214-1892796787-3956688266-3779525307-1193746774-3746984934"));

        // pi-nuc
        wnsMap.put("pi-nuc", Collections.singletonMap("duNV6WxSI8oamhRTgCtbH7pXjOoTIgDl",
                "ms-app://s-1-15-2-1290701865-1353957291-1372331159-3295599533-2631722263-1713803879-248496018"));

        // pan-stage
        wnsMap.put("pan-stage", Collections.singletonMap("FqEIKQq22QXpQQ8OXsc9cZk6wM8jMs54",
                "ms-app://s-1-15-2-779659092-473713123-198008222-3096726411-2351764391-413714272-2413935077"));

        //Pan-Production
        wnsMap.put("prod", Collections.singletonMap("nsjnObuCZcxhTDI+UDCcvG/3AY8r0g3O",
                "ms-app://s-1-15-2-3890458867-3024415941-1395595736-3699935790-65440033-2034019750-2328964143"));

        // pan-laptop1
        wnsMap.put("pan-laptop", Collections.singletonMap("kCeDqljH6Ayvk0zsa0PU3IufJKk/G+sW",
                "ms-app://s-1-15-2-4233441206-1643728984-574441503-2475316883-4250187890-1363024468-3063297652"));
    }

    public static Map<String, String> getWnsConfig(String env) {
        return wnsMap.get(env);
    }
}
