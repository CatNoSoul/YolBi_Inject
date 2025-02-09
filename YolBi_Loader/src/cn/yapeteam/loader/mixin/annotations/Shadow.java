package cn.yapeteam.loader.mixin.annotations;

import cn.yapeteam.loader.utils.ASMUtils;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Shadow {
    class Helper {
        public static boolean isAnnotation(@NotNull AnnotationNode node) {
            return node.desc.substring(1, node.desc.length() - 1).equals(ASMUtils.slash(Shadow.class.getName()));
        }

        public static boolean hasAnnotation(@NotNull MethodNode node) {
            return node.visibleAnnotations != null && node.visibleAnnotations.stream().anyMatch(Shadow.Helper::isAnnotation);
        }

        public static boolean hasAnnotation(@NotNull FieldNode node) {
            return node.visibleAnnotations != null && node.visibleAnnotations.stream().anyMatch(Shadow.Helper::isAnnotation);
        }
    }
}
