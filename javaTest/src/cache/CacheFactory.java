package cache;

import ormtest.dao.PlanDAO;
import ormtest.vo.Plan;
import cache.encache.EnCacheFactory;

/**
 * <p>Title: 业务数据缓存管理类，缓存的具体实现方式由子类决定</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 Apr 27, 2011
 */
public abstract class CacheFactory {
  public static final String PLAN_CACHE = "PLAN_CACHE";
  
  private static CacheFactory instance = null;
  
  public static CacheFactory getInstance() {
    if(instance == null) {
      instance = new EnCacheFactory();
    }
    return instance;
  }
  
  public Plan getCachePlan(int plan_id) {
    Plan plan = getCacheObj(PLAN_CACHE, String.valueOf(plan_id), Plan.class);
    
    if(plan == null) {
      plan = PlanDAO.getPlan(plan_id);
      setCacheObj(PLAN_CACHE, String.valueOf(plan_id), plan);
    }
    return plan;
  }
  
  /**
   * 清空所有的缓存
   */
  public void clearAllCache() {
    clearCache(PLAN_CACHE);
  }
  
  /**
   * 从缓存中读取对象
   * @param cache_name
   * @param key
   * @return
   */
  protected abstract <T>T getCacheObj(String cache_name, String key, Class<T> clz);
  
  /**
   * 设置对象至缓存中
   * @param cache_name
   * @param obj
   */
  protected abstract void setCacheObj(String cache_name,String key, Object obj);
  
  /**
   * 更新缓存内容
   * @param cache_name
   * @param key
   */
  public abstract void updateCacheObj(String cache_name, String key);
  
  /**
   * 清空缓存
   * @param cache_name
   */
  public abstract void clearCache(String cache_name);
}
