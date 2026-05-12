package com.mycompany.plugins.example;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ExamplePlugin")
public class ExamplePlugin extends Plugin {

    private Example implementation;

    @Override
    public void load() {
        implementation = new Example(getContext());
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value", "");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void set(PluginCall call) {
        String key = call.getString("key", "");
        String value = call.getString("value", "");
        implementation.set(key, value);
        call.resolve();
    }

    @PluginMethod
    public void get(PluginCall call) {
        String key = call.getString("key", "");
        String value = implementation.get(key);
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    @PluginMethod
    public void remove(PluginCall call) {
        String key = call.getString("key", "");
        implementation.remove(key);
        call.resolve();
    }
}
