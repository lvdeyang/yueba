package pub.caterpillar.mvc.converter;

public abstract class AbstractBaseVO<V extends AbstractBaseVO, P> {

	private Long id;
	
	private String uuid;
	
	private String updateTime;

	public Long getId() {
		return id;
	}

	public V setId(Long id) {
		this.id = id;
		return (V)this;
	}

	public String getUuid() {
		return uuid;
	}

	public V setUuid(String uuid) {
		this.uuid = uuid;
		return (V)this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public V setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return (V)this;
	}
	
	//注册转换器
	private static <V extends AbstractBaseVO<V, P>, P> VOConverter<V, P> register(Class<V> vocls){
		VOConverter<V, P> converter = new VOConverter<V, P>();
		VOConverterManager.getInstance().register(vocls, converter);
		return converter;
	}
	
	//获取转换器
	public static <V extends AbstractBaseVO<V, P>, P> VOConverter<V, P> getConverter(Class<V> cls){
		
		VOConverter<V, P> converter = null;
		converter = VOConverterManager.getInstance().getConverter(cls);
		
		if(converter == null){
			converter = AbstractBaseVO.<V, P>register(cls);
		}
		
		return converter;
	}
	
	//转换方法
	public abstract <T> T set(P entity) throws Exception;
	
	
}
