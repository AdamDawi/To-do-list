package io.github.adam.language;

class LangDTO //language data transfer object
{
    private int id;
    private String code;

    LangDTO(Language lang)
    {
        this.setId(lang.getId());
        this.setCode(lang.getCode());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
