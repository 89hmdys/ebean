package com.avaje.ebeaninternal.server.type;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.avaje.ebean.text.TextException;
import com.avaje.ebeaninternal.server.core.BasicTypeConverter;

/**
 * ScalarType for Short and short.
 */
public class ScalarTypeShort extends ScalarTypeBase<Short> {

	public ScalarTypeShort() {
		super(Short.class, true, Types.SMALLINT);
	}
	
	public void bind(DataBind b, Short value) throws SQLException {
		if (value == null){
			b.setNull(Types.SMALLINT);
		} else {
			b.setShort(value.shortValue());
		}
	}

	public Short read(DataReader dataReader) throws SQLException {
		
		return dataReader.getShort();
	}
	
	public Object toJdbcType(Object value) {
		return BasicTypeConverter.toShort(value);
	}

	public Short toBeanType(Object value) {
		return BasicTypeConverter.toShort(value);
	}
	
	public String formatValue(Short v) {
        return v.toString();
    }

    public Short parse(String value) {
		return Short.valueOf(value);
	}

	public Short parseDateTime(long systemTimeMillis) {
		throw new TextException("Not Supported");
	}

	public boolean isDateTimeCapable() {
		return false;
	}
	
    public Object readData(DataInput dataInput) throws IOException {
        if (!dataInput.readBoolean()) {
            return null;
        } else {
            short val = dataInput.readShort();
            return Short.valueOf(val);
        }
    }

    public void writeData(DataOutput dataOutput, Object v) throws IOException {
        
        Short value = (Short)v;
        if (value == null){
            dataOutput.writeBoolean(false);
        } else {
            dataOutput.writeBoolean(true);
            dataOutput.writeShort(value.shortValue());            
        }
    }
    
    @Override
    public Object jsonRead(JsonParser ctx, Event event) {
      return (short)ctx.getInt();
    }
    
    public void jsonWrite(JsonGenerator ctx, String name, Object value) {
      ctx.write(name, (Short)value);
    }
}
