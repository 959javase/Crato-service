package com.nash.product.entity.indicator;




public class MonitorCrustQue
{
    private static final long serialVersionUID = 1L;


    private Long id;

    private String ip;


    private String hostname;


    private String pullque;


    private String smalltaskque;


    private String bigtaskque;


    private String sealque;


    private String block;

    private String error;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getIp()
    {
        return ip;
    }
    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public String getHostname()
    {
        return hostname;
    }
    public void setPullque(String pullque)
    {
        this.pullque = pullque;
    }

    public String getPullque()
    {
        return pullque;
    }
    public void setSmalltaskque(String smalltaskque)
    {
        this.smalltaskque = smalltaskque;
    }

    public String getSmalltaskque()
    {
        return smalltaskque;
    }
    public void setBigtaskque(String bigtaskque)
    {
        this.bigtaskque = bigtaskque;
    }

    public String getBigtaskque()
    {
        return bigtaskque;
    }
    public void setSealque(String sealque)
    {
        this.sealque = sealque;
    }

    public String getSealque()
    {
        return sealque;
    }
    public void setBlock(String block)
    {
        this.block = block;
    }

    public String getBlock()
    {
        return block;
    }
    public void setError(String error)
    {
        this.error = error;
    }

    public String getError()
    {
        return error;
    }

}
