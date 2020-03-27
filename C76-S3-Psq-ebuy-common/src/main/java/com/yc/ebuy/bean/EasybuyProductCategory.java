package com.yc.ebuy.bean;

import java.io.Serializable;
import java.util.List;

public class EasybuyProductCategory implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer parentid;

    private Integer type;

    private String iconclass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    /**
     * 	子类集合
     */
    private List<EasybuyProductCategory> children;
    public List<EasybuyProductCategory> getChildren() {
		return children;
	}

	public void setChildren(List<EasybuyProductCategory> children) {
		this.children = children;
	}
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIconclass() {
        return iconclass;
    }

    public void setIconclass(String iconclass) {
        this.iconclass = iconclass == null ? null : iconclass.trim();
    }
    
    public EasybuyProductCategory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}