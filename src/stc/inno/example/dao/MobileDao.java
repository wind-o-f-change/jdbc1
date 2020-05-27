package stc.inno.example.dao;


import stc.inno.example.model.Mobile;

public interface MobileDao {
    Long addMobile(Mobile mobile);

    Mobile getMobileById(Long id);

    boolean updateMobileById(Mobile mobile);

    boolean deleteMobileById(Long id);
}
