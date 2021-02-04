package br.com.krakatoa.protocolizer.format.encoding;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.HexUtils;

@AllArgsConstructor
public class ASCIIMessageFormat implements MessageFormat {

    @Override
    public String execute(String data) {
        return HexUtils.toHexString(data.getBytes());
    }
}
